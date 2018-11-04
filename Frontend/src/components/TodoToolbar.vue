<template>
    <div class="GodoB">
        <TodoExpiredModal :expiredModalStatus=expiredModalStatus />
        <v-toolbar app dark flat fixed>
            <v-btn dark flat @click="addTodo()">
                add Todo
            </v-btn>

            <v-btn dark @click="oepnExpiredModal">
                만료된 Todo
            </v-btn>
        </v-toolbar>
    </div>
</template>

<script>
    import Const from '../Constant'
    import TodoExpiredModal from "./TodoExpiredModal";

    export default {
        name: "TodoToolbar",
        components: {TodoExpiredModal},
        data() {
            return {
                expiredModalStatus : {}
            }
        },
        methods : {
            addTodo() {
                this.$store.commit(Const.ENABLE_ADD_MODAL)
            },
            oepnExpiredModal() {
                this.$store.dispatch(Const.GET_TODO_EXPIRED)
                this.expiredModalStatus = {value:true}
            }
        }
    }
</script>

<style lang="scss" scoped>
    @import "../assets/godoFont";

</style>