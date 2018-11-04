<template>
    <div class="GodoB" style="position: fixed; bottom: 30px; left: 30px;">
        <div>
            <h2>Priority</h2>
            <div><priority priority=1 /> 1 순위 : </div>
            <div><priority priority=2 /> : 2 순위</div>
            <div><priority priority=3 /> : 3 순위</div>
        </div>
    </div>
</template>

<script>
    import Priority from "./priority";
    export default {
        name: "todoPriorityInfo",
        components: {Priority}
    }
</script>

<style lang="scss" scoped >

    @import "../assets/godoFont";

</style>