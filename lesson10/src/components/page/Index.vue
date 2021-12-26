<template>
    <div>

        <FullPost v-for="post in posts" :post="post" :comments="postsComments[post.id]" :users="users" :viewComments="false" :key="post.id"/>
    </div>
</template>

<script>
import FullPost from "@/components/page/Viewers/FullPost";
export default {
    name: "Index",
    props: ["posts", "postsComments", "users"],
    components: {
        FullPost
    },
    inject: ["viewComment"]
}

</script>

<style scoped>

</style>
