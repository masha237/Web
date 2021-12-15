<template>
    <div class="comment">
        <div class="left">
            <div class="author">
                By {{users[comment.userId].name}}
            </div>
        </div>
        <div class="right">
            <div class="comment-content">
                {{ comment.text }}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ViewComment",
    props: ["comment", "users"]
}
</script>

<style scoped>
.comment {
    border: 1px solid var(--border-color);
    border-radius: var(--border-radius);
    overflow: hidden;
    padding: 0.1rem;
    margin-top: 0.25rem;
}

.left {
    padding-left: 0.5rem;
    width: 150px;
}

.left img {
    position: relative;
    top: 5px;
}

.left .author {
    position: center;
}
.right {
    padding-left: 150px;
    font-size: 0.85rem;
    line-height: 2rem;
    padding-right: 0.5rem;
}
</style>