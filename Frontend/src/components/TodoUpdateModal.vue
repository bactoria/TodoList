<template>
    <v-dialog v-model="updateModalStatus" persistent width="800">
        <v-card class="GodoB">
            <v-card-title
                    class="grey lighten-2"
                    primary-title style="font-size: 1.7em;"
            >
                Todo 수정
            </v-card-title>

            <v-card-actions>
                <div style="width:60%; float: left;">
                    <div style="width: 80%;">
                        <v-text-field label="제목" v-model="updateModal.title"></v-text-field>
                        <v-spacer class="spacer"></v-spacer>
                        <v-textarea label="내용" v-model="updateModal.content"></v-textarea>
                        <v-spacer class="spacer"></v-spacer>
                        <input type="radio" value="1" v-model="updateModal.priority">
                        <v-icon color="red" style="margin: 0 30px 0 5px">filter_1</v-icon>
                        <input type="radio" value="2" v-model="updateModal.priority">
                        <v-icon color="blue" style="margin: 0 30px 0 5px">filter_2</v-icon>
                        <input type="radio" value="3" v-model="updateModal.priority">
                        <v-icon color="grey" style="margin: 0 30px 0 5px">filter_3</v-icon>
                        <v-spacer class="spacer"></v-spacer>
                    </div>
                    <v-spacer class="spacer"></v-spacer>
                    <div>
                        <v-btn small color="primary" flat @click="updateTodo()">수정</v-btn>
                        <v-btn small color="primary" flat @click="closeModal()">취소</v-btn>
                        <v-btn small :disabled="nonClosingDate()" color="primary" flat
                               @click="deleteClosingDate()">
                            마감일 없애기
                        </v-btn>
                    </div>
                </div>

                <div style="width: 40%; float:left;">
                    <datepicker :language="ko" @selected="asd()" v-model="updateModal.closingDate" :inline="true"
                                :open-date="updateModal.closingDate" style="margin: 20px 0 20px 0;"></datepicker>
                </div>

            </v-card-actions>
        </v-card>
    </v-dialog>
</template>
<script>
    import Const from '../Constant'
    import {mapGetters} from 'vuex';
    import Datepicker from 'vuejs-datepicker';
    import {ko} from 'vuejs-datepicker/dist/locale'

    export default {
        name: "TodoUpdateModal",
        data() {
            return {
                ko: ko,
            }
        },
        components: {
            Datepicker
        },
        computed:
            mapGetters({
                updateModalStatus: 'updateModalStatus'
            }),
        props: {
            updateModal: Object, todoLink: String
        },
        methods: {
            updateTodo() {
                let post = {
                    title: this.updateModal.title,
                    content: this.updateModal.content,
                    closingDate: this.updateModal.closingDate,
                    priority: this.updateModal.priority
                }

                let link = this.todoLink

                let payload = {post, link}

                this.$store.dispatch(Const.UPDATE_TODO, payload)
            },
            closeModal() {
                this.$store.commit(Const.DISABLE_UPDATE_MODAL)
            },
            deleteClosingDate() {
                this.updateModal.closingDate = null
                this.date2 = "없음."
            },
            nonClosingDate() {
                return this.updateModal.closingDate == null
            },
            asd() {
                console.log("tq")
                console.log(this.updateModal.closingDate)
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