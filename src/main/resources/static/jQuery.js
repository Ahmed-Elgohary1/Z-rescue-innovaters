$(document).ready(function() {
  $('#rankForm').submit(function(event) {
    event.preventDefault();
    var selectedRank = $('#rankSelect').val();


    $.ajax({
      url: 'http://localhost:8080/api/Issues/s/' + selectedRank,
      type: 'GET',
      success: function(response) {
        // Handle the response and update the UI accordingly
        var resultContainer = $('#resultContainer');
        resultContainer.empty();

            console.log("selectedRank");


        if (response.length > 0) {
          var list = $('<ul>').addClass('list-group');

          response.forEach(function(record) {
            var listItem = $('<li>').addClass('list-group-item').text(record);
            list.append(listItem);
          });

          resultContainer.append(list);
        } else {
          resultContainer.text('No records found with the selected rank.');
        }
      },
      error: function() {
        // Handle errors, if any
        console.log('An error occurred during the AJAX request.');
      }
    });
  });
});