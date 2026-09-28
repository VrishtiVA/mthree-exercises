//File for use in Ajax lesson

//Once page loaded and ready
$(document).ready(function() {

    //Ajax method
    $.ajax({

        //Type of http request we want to send
        type: 'GET',

        //Endpoint
        url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contacts',
        
        //What to do if request succeeds
        //We're receiving an array of contacts from server
        success: function(contactArray) {
            var contactsDiv = $('#allContacts');

            //For each contact, add to display
            $.each(contactArray, (index, contact) => {
                contactsDiv.append(`
                    <p>
                        Name: ${contact.firstName} ${contact.lastName}
                        <br/>
                        Company: ${contact.company}
                        <br/>
                        Email: ${contact.email}
                        <br/>
                        Phone: ${contact.phone}
                        <br/>
                    </p>
                    <hr>
                `);
            });

        },

        //What to do if request fails
        error: function() {
            alert('FAILURE!');
        }

    })

})