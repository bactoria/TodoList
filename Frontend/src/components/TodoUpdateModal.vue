<template>
    <div class="text-xs-center">
        <v-dialog v-model="updateModalStatus" persistent width="500">
            <v-card>
                <v-card-title
                        class="headline grey lighten-2"
                        primary-title
                >
                    Todo 수정
                </v-card-title>

                <v-card-text>
                    <v-text-field v-model="modalTodo.todo.title" label="제목"></v-text-field>
                    <v-text-field v-model="modalTodo.todo.content" label="내용"></v-text-field>
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" flat @click="updateTodo()">수정</v-btn>
                    <v-btn color="primary" flat @click="closeModal()">취소</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
    import Const from '../Constant'
    import {mapGetters} from 'vuex';

    export default {
        name: "TodoUpdateModal",
        computed:
            mapGetters({
                updateModalStatus: 'updateModalStatus'
            }),
        props: {
            modalTodo: Object
        },
        methods: {
            updateTodo() {
                let post = {
                    title: this.modalTodo.todo.title,
                    content: this.modalTodo.todo.content,
                    closingDate: this.modalTodo.todo.closingDate
                }

                let link = this.modalTodo._links.self.href

                let payload = {post, link}

                this.$store.dispatch(Const.UPDATE_TODO, payload)
            },
            closeModal() {
                this.$store.commit(Const.DISABLE_UPDATE_MODAL)
            }
        }
    }
</script>

<style scoped>

</style>