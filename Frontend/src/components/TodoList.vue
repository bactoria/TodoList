<template>
    <div>
        <v-btn color="red lighten-2" dark @click="addTodo()">
            Add Todo
        </v-btn>
        <TodoAddModal/>
        <TodoUpdateModal :modal-todo="modalTodo"/>
        <v-layout>
            <v-flex>
                <v-card v-for="todo in todoList" :key="todo.todo.id"
                        style="border: 1px solid darkgrey; margin: 2vh; width: 50vw;">

                    <v-layout row wrap>
                        <v-flex xs8>
                            <div>
                                <h3 class="headline mb-0">{{todo.todo.title}}</h3>
                                <div>{{todo.todo.content}}</div>
                                <!--TODO : 마감기한-->
                            </div>
                        </v-flex>
                        <v-flex xs3>
                            <v-btn flat color="orange" @click="updateTodo(todo)">수정</v-btn>
                            <v-btn flat color="orange" @click="deleteTodo(todo._links.self)">삭제</v-btn>
                            <v-btn v-if="!todo.todo.completedTodo" flat color="orange"
                                   @click="completedTodo(todo._links.self)">완료
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

    export default {
        name: "TodoList",
        components: {TodoAddModal, TodoUpdateModal},
        computed: mapGetters({
            todoList: 'todoList'
        }),
        data(){
            return {
                modalTodo : {}
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
            addTodo() {
                this.$store.commit(Const.ENABLE_ADD_MODAL)
            },
            updateTodo(todo) {
                this.modalTodo = JSON.parse(JSON.stringify(todo))
                this.$store.commit(Const.ENABLE_UPDATE_MODAL)
            }
        }
    }
</script>

<style scoped>

</style>