$(async function() {
    deleteUser();
});
function deleteUser(){
    const deleteForm = document.forms["formDeleteUser"];
    deleteForm.addEventListener("submit", event => {
        event.preventDefault();
        fetch("http://localhost:8080/api/users/" + deleteForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => {
                $('#deleteFormCloseButton').click();
                allUsers();
            })
    })
}
