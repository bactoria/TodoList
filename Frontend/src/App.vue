<template>
  <v-app align="center">

    <v-content>
      <TodoColorInfo/>
      <TodoPriorityInfo/>
      <TodoToolbar/>
      <TodoList/>
    </v-content>

  </v-app>
</template>

<script>
    import TodoList from './components/TodoList'
    import TodoToolbar from "./components/TodoToolbar";
    import TodoPriorityInfo from "./components/todoPriorityInfo";
    import TodoColorInfo from "./components/TodoColorInfo";

    export default {
        name: 'App',
        components: {
            TodoColorInfo,
            TodoPriorityInfo,
            TodoToolbar,
            TodoList
        },
        data() {
            return {
                //
            }
        }
    }
</script>
