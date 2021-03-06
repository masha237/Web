<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :users="users" :posts="posts"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            users: [],
            posts: []
        }
    },
    provide() {
        return {
            getLength: this.getLength,
            viewPost: this.viewPost
        }
    },
    methods: {
        getLength(array) {
            return Object.keys(array).length;
        },
        viewPost: function (post) {
            this.$root.$emit("viewPost", post);
        },
        update() {
            axios.get("/api/1/posts").then(response => {
                this.posts = response.data;
            });

            axios.get("/api/1/users").then(response => {
                this.users = response.data;
            });
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
            this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }

        this.update();


    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }
            if (login === "") {
                this.$root.$emit("onEnterValidationError", "Login is required");
                return;
            }

            axios.post("/api/1/jwt", {
                    login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data);
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });

        this.$root.$on("onWritePost", (title, text) => {
            if (this.user) {

                const userId = this.user.id;
                if (!title || title.length < 5) {
                    this.$root.$emit("onWritePostValidationError", "Title is too short");
                } else if (!text || text.length < 10) {
                    this.$root.$emit("onWritePostValidationError", "Text is too short");
                } else {
                    axios.post("/api/1/posts", {
                        title, text, userId
                    }).then(() => {
                        this.update();
                        this.$root.$emit("onChangePage", "Index");
                    }).catch(error => {
                        this.$root.$emit("onWritePostValidationError", error.response.data);
                    });
                }
            } else {
                this.$root.$emit("onWritePostValidationError", "No access");
            }
        });

        this.$root.$on("onRegister", (login, name, password) => {
            if (login === "") {
                this.$root.$emit("onRegisterValidationError", "Login's should contain 3 to 16 characters");
            } else if (name === "") {
                this.$root.$emit("onRegisterValidationError", "Name's should contain 1 to 32 characters");
            } else if (password === "") {
                this.$root.$emit("onRegisterValidationError", "Password should contain 4 to 32 characters");
            } else {
                axios.post("/api/1/users", {
                    login, name, password
                }).then(() => {
                    this.$root.$emit("onEnter", login, password);
                }).catch(error => {
                    this.$root.$emit("onRegisterValidationError", error.response.data);
                });
            }
        });
    }
}
</script>

<style>
#app {

}
</style>
