<template>
    <v-card outlined>
        <v-card-title>
            CancelApproved
        </v-card-title>

        <v-card-text>
            <String label="BorrowId" v-model="value.borrowId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="cancelApproved"
            >
                CancelApproved
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
        name: 'CancelApprovedCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.borrowId = '';
        },
        watch: {
        },
        methods: {
            cancelApproved() {
                this.$emit('cancelApproved', this.value);
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

