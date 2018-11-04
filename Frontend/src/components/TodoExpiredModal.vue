<template>
    <div>
        <v-dialog scrollable v-model="expiredModalStatus.value" max-width="500">
            <v-card>
                <v-card-title
                        class="GodoB grey lighten-2"
                        primary-title style="font-size: 1.7em; margin-bottom: 20px;"
                >
                    만료된 Todo
                </v-card-title>

                <v-card-text>
                    <div class="GodoB" style="width: 100%;">

                        <v-list v-for="todo in todoListExpired" :key="todo.todo.id"
                                style="border:1px solid grey; margin: 5px 0 5px 0; background-color: darkorange">
                            <v-container>
                                <v-layout row wrap>
                                    <v-flex xs1>
                                        <priority :priority="todo.todo.priority"/>
                                    </v-flex>
                                    <v-flex xs11>
                                        <div style="text-align: left;">{{todo.todo.closingDate}}</div>
                                        <div style="text-align: left; font-size: 1.7em;">{{todo.todo.title}}</div>
                                        <div style="text-align: left;">{{todo.todo.content}}</div>
                                    </v-flex>
                                </v-layout>
                            </v-container>
                        </v-list>
                    </div>

                </v-card-text>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
    import {mapGetters} from 'vuex';
    import Priority from "./priority";

    export default {
        name: "TodoExpiredModal",
        components: {Priority},
        computed:
            mapGetters({
                todoListExpired: 'todoListExpired'
            }),
        props: {
            expiredModalStatus: Object
        }
    }
</script>

<style lang="scss" scoped>
    @import "../assets/godoFont";

</style>