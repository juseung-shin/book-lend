<template>
    <v-card outlined>
        <v-card-title>
            Update
        </v-card-title>

        <v-card-text>
            <String label="BorrowId" v-model="value.borrowId" :editMode="editMode"/>
            <String label="Status" v-model="value.status" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="update"
            >
                Update
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'UpdateCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.borrowId = '';
            this.value.status = '';
        },
        watch: {
        },
        methods: {
            update() {
                this.$emit('update', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

