<template>
  <v-app align="center">

    <h1>Todo List</h1>
    <v-content>
      <TodoList/>
    </v-content>

  </v-app>
</template>

<script>
    import TodoList from './components/TodoList'

    export default {
        name: 'App',
        components: {
            TodoList
        },
        data() {
            return {
                //
            }
        }
    }
</script>
