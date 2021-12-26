<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index  :posts="viewAllPosts" :users="users" v-if="page === 'Index'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Users :users="viewUsers" v-if="page === 'Users'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <FullPost :post="post" :users="users" :viewComments="true" v-if="page === 'FullPost'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Enter from "./main/Enter";
import Register from "./main/Register";
import Users from "./main/Users";
import WritePost from "./main/WritePost";
import FullPost from "./main/Viewers/FullPost";

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
        Register,
        Enter,
        Index,
        Sidebar,
        Users,
        WritePost
    },
    props: ["users", "posts"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
        viewAllPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        viewUsers: function () {
            return Object.values(this.users).sort((a, b) => b.id - a.id);
        }
    }, beforeCreate() {
        this.$root.$on("viewPost", (post) => {
            this.post = post;
            this.$root.$emit("onChangePage", "FullPost");
        })
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
