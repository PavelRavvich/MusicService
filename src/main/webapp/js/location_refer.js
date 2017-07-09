/**
 * Update location sets without update all page.
 * When select country then update set of cities.
 */
$(document).ready(function() {
    $('#country').change(function(event) {
        var country = $("select#country").val();
        $.get('citiesByCountry', {
            country : country
        }, function(response) {

            var select = $('#city');
            select.find('option').remove();
            $.each(response, function(index, value) {
                $('<option>').val(value).text(value).appendTo(select);
            });
        });
    });
});
