
//Get API Keys
const apiKeyFile = await fetch("./apiKey.json");
const apiKeys = await apiKeyFile.json();
const openWeatherAPIKey = apiKeys["open-weather-api-key"];

function validateZipCode(zipCode) {
    
    //No zip code, then invalid.
    if (zipCode === undefined) return null;

    //Otherwise validate
    //Note: In JS, it is .length on strings too
    zipCode = zipCode.trim();
    if (zipCode.length != 5) {
        return null;
    } else {
        return zipCode;
    }
}

function getCurrentConditions(zipCode, units) {
    $.ajax({
        type: 'GET',

        //USA by Default, units are optional
        url: `https://api.openweathermap.org/data/2.5/weather?zip=${zipCode}&units=${units}&appid=${openWeatherAPIKey}`,

        success: function(response) {
            displayCurrentWeatherConditions(response, units);
        },

        error: function() {
            $("#weatherResults")
                .hide();
            $("#weatherFeedbackAlert")
                .html("Could not fetch the current weather for this zip code.")
                .show();
        }
    })
}

function get3Hour5DayForecast(zipCode, units) {
    $.ajax({
        type: 'GET',

        //USA by Default, units are optional
        url: `https://api.openweathermap.org/data/2.5/forecast?zip=${zipCode}&units=${units}&appid=${openWeatherAPIKey}`,

        success: function(response) {
            display5DayForecast(response, units);
        },

        error: function() {
            $("#weatherResults")
                .hide();
            $("#weatherFeedbackAlert")
                .html("Could not fetch the 5 day weather forecast for this zip code.")
                .show();
        }
    })
}

//Once file ready
// $(document).ready(() => {

// })

//Alright for now.
$("#weatherZipCode").on("input", () => {
    $("#weatherFeedbackAlert").hide();
});

//On weather submit
$("#weatherSubmitBtn").on("click", () => {
    
    //Get inputs
    let zipCode = $("#weatherZipCode").val();
    let units = $("#weatherUnits").val().toLowerCase();

    //Validate fields
    zipCode = validateZipCode(zipCode);

    if (zipCode == null) {
        $("#weatherResults")
            .hide();
        $("#weatherFeedbackAlert")
            .html("Zip code: please enter a 5-digit zip code.")
            .show();
        return;
    }

    //Otherwise trigger
    getCurrentConditions(zipCode, units);
    get3Hour5DayForecast(zipCode, units);
    $("#weatherResults").show();
})

function displayCurrentWeatherConditions(currentConditions, units) {

    //Display city name
    $("#weatherResultsCity").text(currentConditions.name);

    //Display current weather
    $("#weatherCurrentIcon").attr("src", `http://openweathermap.org/img/w/${currentConditions.weather[0].icon}.png`);
    $("#weatherCurrentDescription").html(currentConditions.weather[0].main + ": " + currentConditions.weather[0].description);

    //Display weather breakdown
    $("#weatherCurrentTemperature").html(currentConditions.main.temp + " " + (units == "metric" ? "C" : "F"));
    $("#weatherCurrentHumidity").html(currentConditions.main.humidity + " %");
    $("#weatherCurrentWind").html(currentConditions.wind.speed + " " + (units == "metric" ? "km/hour" : "miles/hour"));
}

function display5DayForecast(weatherForecast, units) {
    let forecastContainer = $("#weatherFiveDayForecastContainer");

    let prevDate = new Date(Date.now());
    $.each(weatherForecast.list, (i, dayForecast) => {
        
        //Can improve by not using night only
        let date = new Date(dayForecast.dt * 1000);
        if (prevDate.getDate() == date.getDate())
            return;
        else
            prevDate = date;

        forecastContainer.append(`
            <li class="d-flex flex-column gap-4 mt-3">
                <span>${date.toLocaleDateString()}</soan>
                <div>
                    <img src="http://openweathermap.org/img/w/${dayForecast.weather[0].icon}.png"/>
                    <span>${dayForecast.weather[0].main}</span>
                </div>
                <!-- <span>
                    H ${dayForecast.main.temp_min} ${(units == "metric" ? "C" : "F")}
                    L ${dayForecast.main.temp_max} ${(units == "metric" ? "C" : "F")}
                </span> -->
            </li>
        `);
    });
}