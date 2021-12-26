<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index :posts="viewAllPosts" :postsComments="postsComments" :users="users" v-if="page === 'Index'"/>
            <Enter v-if="page === 'Enter'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Register v-if="page === 'Register'"/>
            <FullPost :post="post" :comments="postsComments[post.id]" :users="users" :viewComments="true" v-if="page === 'FullPost'"/>
            <Users :users="viewUsers" v-if="page === 'Users'"/>

        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "@/components/page/Register";
import Users from "@/components/page/Users";
import FullPost from "@/components/page/Viewers/FullPost";



export default {
    name: "Middle",
    data: function () {
        return {
            post: null,
            page: "Index"
        }
    },
    components: {
        FullPost,
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost,
        Register,
        Users
    },
    props: ["posts", "users", "postsComments"],
    inject: ["viewComment"],
    computed: {
        viewAllPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
        viewUsers: function () {
            return Object.values(this.users).sort((a, b) => b.id - a.id);
        }
    }, beforeCreate() {
        this.$root.$on("viewPost", (postId) => {
            this.post = this.posts[postId];
            this.$root.$emit("onChangePage", "FullPost");
        })
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
