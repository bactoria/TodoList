<template>
    <div class="text-xs-center">
        <v-dialog v-model="addModalStatus" persistent width="500">
            <v-card>
                <v-card-title
                        class="headline grey lighten-2"
                        primary-title
                >
                    새로운 Todo 생성
                </v-card-title>

                <v-card-text>
                    <v-text-field v-model="title" placeholder="제목"></v-text-field>
                    <v-text-field v-model="content" placeholder="내용"></v-text-field>
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" flat @click="addTodo()">추가</v-btn>
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
        name: "TodoAddModal",
        computed:
            mapGetters({
                addModalStatus: 'addModalStatus'
            }),
        data() {
            return {
                title: '',
                content: '',
                closingDate: ''
            }
        },
        methods: {
            addTodo() {
                let post = {
                    title: this.title,
                    content: this.content,
                    closingDate: this.closingDate
                }

                this.title = ''
                this.content = ''
                this.closingDate = ''

                this.$store.dispatch(Const.ADD_TODO, post)
            },
            closeModal() {
                this.$store.commit(Const.DISABLE_ADD_MODAL)
            }
        }
    }
</script>

<style scoped>

</style>