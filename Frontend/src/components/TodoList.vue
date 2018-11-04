<template>
    <div class="GodoM">
        <TodoAddModal/>
        <TodoUpdateModal :update-modal="updateModal" :todo-link="todoLink"/>
        <v-layout>
            <v-flex>
                <v-card :class="{ expiredTodo: expiredTodo(todo.todo.completedTodo, todo.todo.closingDate), completedTodo: todo.todo.completedTodo}"
                        v-for="todo in todoList.slice().reverse()" :key="todo.todo.id"
                        style="border: 1px solid darkgrey; margin: 2vh; width: 50vw; ">
                    <v-layout row wrap>
                        <v-flex xs1>
                            <priority :priority=todo.todo.priority />
                        </v-flex>
                        <v-flex xs8>
                            <div>
                                <div>{{todo.todo.closingDate == null ? '- 만료기간 없음 -' : todo.todo.closingDate}}</div>
                                <h2>{{todo.todo.title}}</h2>
                                <div>{{todo.todo.content}}</div>
                            </div>
                        </v-flex>
                        <v-flex xs3>
                            <div>
                                <v-btn flat small fab color="blue" @click="updateTodo(todo)">
                                    <v-icon dark>edit</v-icon>
                                </v-btn>
                                <v-btn flat small fab color="red" @click="deleteTodo(todo._links.self)">
                                    <v-icon dark>remove</v-icon>
                                </v-btn>
                            </div>
                            <v-btn flat small color="#00FA9A" v-if="!todo.todo.completedTodo"
                                   @click="completedTodo(todo._links.self)">
                                <v-icon dark>check</v-icon>
                            </v-btn>
                        </v-flex>
                    </v-layout>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex';
    import Const from '../Constant'
    import TodoUpdateModal from "./TodoUpdateModal";
    import TodoAddModal from "./TodoAddModal";
    import Priority from "./priority";

    export default {
        name: "TodoList",
        components: {Priority, TodoAddModal, TodoUpdateModal},
        computed: mapGetters({
            todoList: 'todoList'
        }),
        data() {
            return {
                updateModal : {},
                todoLink : ''
            }
        },
        mounted() {
            this.$store.dispatch(Const.GET_TODO_ALL);
        },
        methods: {
            deleteTodo(self) {
                this.$store.dispatch(Const.DELETE_TODO, self)
            },
            completedTodo(self) {
                this.$store.dispatch(Const.COMPLETED_TODO, self)
            },
            updateTodo(todo) {
                this.todoLink = todo._links.self.href
                this.updateModal = JSON.parse(JSON.stringify(todo.todo))
                this.$store.commit(Const.ENABLE_UPDATE_MODAL)
            },
            expiredTodo(completedTodo, closingDate) {
                return !completedTodo && closingDate != null && new Date(closingDate) < new Date().setHours(0, 0, 0, 0)
            }
        }
    }
</script>

<style lang="scss" scoped>

    @import "../assets/godoFont";

    .completedTodo {
        background-color: mediumspringgreen;
    }

    .expiredTodo {
        background-color: darkorange;
    }

</style>