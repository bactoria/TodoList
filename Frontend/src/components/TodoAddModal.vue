<template>
    <div>
        <v-dialog v-model="addModalStatus" persistent width="800">
            <v-card class="GodoB">
                <v-card-title
                        class="grey lighten-2"
                        primary-title style="font-size: 1.7em;"
                >
                    새로운 Todo 생성
                </v-card-title>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <div style="width:60%; float: left;">
                        <div style="width: 80%;">
                            <v-text-field label="제목" v-model="addModal.title"></v-text-field>
                            <v-spacer class="spacer"></v-spacer>

                            <v-textarea label="내용" v-model="addModal.content"></v-textarea>
                            <v-spacer class="spacer"></v-spacer>

                            <input type="radio" value="1" v-model="addModal.priority">
                            <v-icon color="red" style="margin: 0 30px 0 5px">filter_1</v-icon>
                            <input type="radio" value="2" v-model="addModal.priority">
                            <v-icon color="blue" style="margin: 0 30px 0 5px">filter_2</v-icon>
                            <input type="radio" value="3" v-model="addModal.priority">
                            <v-icon color="grey" style="margin: 0 30px 0 5px">filter_3</v-icon>
                            <v-spacer class="spacer"></v-spacer>
                        </div>
                            <v-spacer class="spacer"></v-spacer>
                        <div>
                            <v-btn small color="primary" flat @click="addTodo()">추가</v-btn>
                            <v-btn small color="primary" flat @click="closeModal()">취소</v-btn>
                            <v-btn small :disabled="nonClosingDate()" color="primary" flat @click="deleteClosingDate()">
                                마감일 없애기
                            </v-btn>
                        </div>
                    </div>
                    <div style="width: 40%; float:left;">
                        <datepicker :language="ko" v-model="addModal.closingDate" :inline="true"
                                    :open-date="today" style="margin: 20px 0 20px 0;"></datepicker>
                    </div>


                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
    import Const from '../Constant'
    import {mapGetters} from 'vuex';
    import Datepicker from 'vuejs-datepicker';
    import {ko} from 'vuejs-datepicker/dist/locale'

    export default {
        name: "TodoAddModal",
        computed:
            mapGetters({
                addModalStatus: 'addModalStatus', addModal: 'addModal'
            }),
        components: {
            Datepicker
        },
        data() {
            return {
                ko: ko,
                true: true,
                today: new Date()
            }
        },
        methods: {
            addTodo() {
                let post = {
                    title: this.addModal.title,
                    content: this.addModal.content,
                    closingDate: this.addModal.closingDate,
                    priority: this.addModal.priority
                }

                this.$store.dispatch(Const.ADD_TODO, post)
            },
            closeModal() {
                this.$store.commit(Const.INIT_ADD_MODAL)
                this.$store.commit(Const.DISABLE_ADD_MODAL)
            },
            deleteClosingDate() {
                this.addModal.closingDate = null
            },
            nonClosingDate() {
                return this.addModal.closingDate == null
            }
        }
    }
</script>

<style lang="scss" scoped>
    @import "../assets/godoFont";

    .spacer {
        height: 10px;
    }
</style>