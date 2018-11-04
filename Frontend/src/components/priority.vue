<template>
    <span>
        <v-icon v-if="priority==1" color="red">filter_{{priority}}</v-icon>
        <v-icon v-if="priority==2" color="blue">filter_{{priority}}</v-icon>
        <v-icon v-if="priority==3" color="grey">filter_{{priority}}</v-icon>
    </span>
</template>

<script>
    export default {
        name: "priority",
        props: ['priority']
    }
</script>

<style scoped>

</style>