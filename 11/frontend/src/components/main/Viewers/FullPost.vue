<template>
    <div>
        <article>
            <div class="title">{{post.title}}</div>
            <div class="information">By {{post.user.name}}, 5 дней назад</div>
            <div class="body">{{post.text}}</div>

            <div class="footer">
                <div class="left">
                    <a href="#"><img src="../../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/></a>
                    <span class="positive-score">+173</span>
                    <a href="#"><img src="../../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/></a>
                </div>
                <div class="right">
                    <a href="#"><img src="../../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/></a>
                    5 дней назад
                    <a href="#"><img src="../../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/></a>
<!--                    <a href="#">{{getLength(comments)}}</a>-->
                </div>
            </div>
        </article>
<!--        <template v-if="viewComments">
            <div>
                <ViewComment v-for="comment in comments" :comment="comment" :users="users" :key="comment.id"/>
            </div>
        </template>-->
    </div>
</template>

<script>
export default {
    name: "FullPost",
    props: ["post", "users", "viewComments"],
    inject: ["getLength"]
}
</script>

<style scoped>
section {
}
</style>