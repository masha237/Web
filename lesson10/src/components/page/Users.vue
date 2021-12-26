<template>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
                <UserInTable v-for="user in users" :user="user" :key="user.id"/>
            </tbody>
        </table>
    </div>
</template>

<script>
import UserInTable from "@/components/page/Viewers/UserInTable";

export default {
    name: "Users",
    props: ["users"],
    components: {
        UserInTable
    }
}
</script>

<style scoped>
table {
    width: 100%;
    text-align: center;
    border-collapse: collapse;
}

.caption {
    background-color: var(--datatable-color);
    font-size: 0.9rem;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    padding: 0.25rem 0.5rem;
}

td, th {
    border: 1px solid var(--datatable-color);
    padding: 0.5em;
    font-size: 0.8rem;
}

tbody tr:nth-child(2n-1) {
    background-color: var(--datatable-dark-cell-color);
}

</style>
