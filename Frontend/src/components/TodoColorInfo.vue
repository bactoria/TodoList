<template>
    <div class="GodoB" style="position: fixed; top: 50vh; right: 30px;">
        <div>
            <div><v-btn class="todoColorInfoBtn" style="background-color: darkorange" /> : 만료된 Todo</div>
            <div><v-btn class="todoColorInfoBtn" style="background-color: mediumspringgreen"/> : 완료한 Todo</div>
        </div>
    </div>

</template>

<script>
    export default {
        name: "TodoColorInfo"
    }
</script>

<style lang="scss" scoped >

    @import "../assets/godoFont";

    .todoColorInfoBtn {
        height: 1em;
        width: 10px;
    }

</style>