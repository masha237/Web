<template>
    <div>
        <FullPost v-for="post in posts" :post="post" :comments="viewComment(post)" :users="users" :viewComments="false" :key="post.id"/>
    </div>
</template>

<script>
import FullPost from "@/components/page/Viewers/FullPost";
export default {
    name: "Index",
    props: ["posts", "comments", "users"],
    components: {
        FullPost
    },
    methods: {
        viewComment: function (post) {
            return Object.values(this.comments).filter(c => c.postId === post.id).sort((a, b) => a.id - b.id);
        }
    }
}

</script>

<style scoped>

</style>
