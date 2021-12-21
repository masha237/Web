<template>
    <div id="app">
        <Header :user="users[userId]"/>
        <Middle :posts="posts" :users="users" :postsComments="postsComments"/>

        <Footer :postsCount="getLength(posts)" :usersCount="getLength(users)"/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        let postsComments = Object.values(this.$root.$data.posts).reduce((obj, post) => ({...obj, [post.id]: []}), {});
        Object.values(this.$root.$data.comments).forEach(comment => {
            postsComments[comment.postId].push(comment);
        });
        this.$root.$data["postsComments"] = postsComments;
        return this.$root.$data;
    },
    provide() {
        return {
            getLength: this.getLength,
            viewComment: this.viewComment,
            viewPost: this.viewPost
        }
    },
    methods: {
        getLength(array) {
            return Object.keys(array).length;
        },
        viewComment: function (post) {
            return Object.values(this.comments).filter(c => c.postId === post.id).sort((a, b) => a.id - b.id);
        },
        viewPost: function (idPost) {
                this.$root.$emit("viewPost", idPost);
        }
    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }
            const users = Object.values(this.users).filter(u => u.login === login);
            if (users.length === 0) {
                this.$root.$emit("onEnterValidationError", "No such user");
            } else {
                this.userId = users[0].id;
                this.$root.$emit("onChangePage", "Index");
            }
        });

        this.$root.$on("onLogout", () => this.userId = null);

        this.$root.$on("onWritePost", (title, text) => {
            if (this.userId) {
                if (!title || title.length < 5) {
                    this.$root.$emit("onWritePostValidationError", "Title is too short");
                } else if (!text || text.length < 10) {
                    this.$root.$emit("onWritePostValidationError", "Text is too short");
                } else {
                    const id = Math.max(...Object.keys(this.posts)) + 1;
                    this.$root.$set(this.posts, id, {
                        id, title, text, userId: this.userId
                    });
                }
            } else {
                this.$root.$emit("onWritePostValidationError", "No access");
            }
        });

        this.$root.$on("onRegister", (login, name) => {
            if (!login || login.length < 3 || login.length > 16) {
                this.$root.$emit("onRegisterValidationError", "Login's should contain 3 to 16 characters");
            } else if (!name || name.length < 1 || name.length > 32) {
                this.$root.$emit("onRegisterValidationError", "Name's should contain 1 to 32 characters");
            } else if (!/^[a-z]+$/i.test(login)) {
                this.$root.$emit("onRegisterValidationError", "Login should contain only Latin letters");
            } else if (!/^[a-z][a-z ]+$/i.test(name)) {
                this.$root.$emit("onRegisterValidationError", "Name should contain only Latin letters and spaces. Name should begin from letters");
            } else {
                const users = Object.values(this.users).filter(u => u.login === login);
                if (users.length !== 0) {
                    this.$root.$emit("onRegisterValidationError", "Login is already used");
                    return;
                }
                const id = Math.max(...Object.keys(this.users).map((id) => parseInt(id))) + 1;
                this.$root.$set(this.users, id, {
                    id, login, name, admin: false
                });
                this.$root.$emit("onChangePage", "Enter");
            }

        });

        this.$root.$on("onEditPost", (id, text) => {
            if (this.userId) {
                if (!id) {
                    this.$root.$emit("onEditPostValidationError", "ID is invalid");
                } else if (!text || text.length < 10) {
                    this.$root.$emit("onEditPostValidationError", "Text is too short");
                } else {
                    let posts = Object.values(this.posts).filter(p => p.id === parseInt(id));
                    if (posts.length) {
                        posts.forEach((item) => {
                            item.text = text;
                        });
                    } else {
                        this.$root.$emit("onEditPostValidationError", "No such post");
                    }
                }
            } else {
                this.$root.$emit("onEditPostValidationError", "No access");
            }
        });
    }
}
</script>

<style>
#app {

}
</style>
