<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2021 by Mike Mirzayanov
        Posts: {{postsCount}}
        Users: {{usersCount}}
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["postsCount", "usersCount"]
}
</script>

<style scoped>

</style>