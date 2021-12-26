<template>
    <div>
        <FullPost v-for="post in posts" :post="post" :users="users" :viewComments="false" :key="post.id"/>
    </div>
</template>

<script>
import FullPost from "./Viewers/FullPost";
export default {
    name: "Index",
    props: ["posts", "users"],
    inject: ["viewPost"],
    components: {
        FullPost
    }
}
</script>

<style scoped>
</style>