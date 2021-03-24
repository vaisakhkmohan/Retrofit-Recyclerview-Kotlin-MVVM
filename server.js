var express = require('express');
var app = express();
var fs = require("fs");

var bodyParser = require('body-parser')
app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({
    extended: true
}));

//Arbitrary ID manager since we don't use a database
var index = 5;

// Initializing Destinations Array.. It will behave like a dummy database 
var destinations = [{
    "id": 1,
    "city": "Thrissur",
    "description": "Thrissur is a city in the south Indian state of Kerala. It's known for sacred sites and colorful festivals. In the center is Vadakkumnathan Temple, dedicated to Lord Shiva and adorned with murals. The ornate, Indo-Gothic Our Lady of Dolours Basilica is nearby. To the north, Thiruvambady Temple is home to several elephants. Sakthan Thampuran Palace houses an archaeology museum with bronze statues and ancient coins.",
    "country" : "India"
}, {
    "id": 2,
    "city": "Ernakulam",
    "description": "Sprawling, residential Ernakulam is known for Marine Drive, a busy waterfront promenade where boats offer backwater cruises. The Kerala Folklore and Hill Palace museums explore local heritage through art and antiquities. Shops along Broadway and on MG Road sell fabrics, crafts, and spices, while modern Lulu Mall also has a cinema and an ice rink. Simple eateries serve Keralan specialties and South Indian seafood.",
    "country" : "India"
}, {
    "id": 3,
    "city": "Trivantrum",
    "description": "Thiruvananthapuram (or Trivandrum) is the capital of the southern Indian state of Kerala. It's distinguished by its British colonial architecture and many art galleries. It’s also home to Kuthira Malika (or Puthen Malika) Palace, adorned with carved horses and displaying collections related to the Travancore royal family, whose regional capital was here from the 18th–20th centuries.",
    "country" : "India"
}, {
    "id": 4,
    "city": "Kozhikode",
    "description": "Kozhikode is a coastal city in the south Indian state of Kerala. It was a significant spice trade center and is close to Kappad Beach, where Portuguese explorer Vasco da Gama landed in 1498. The central Kozhikode Beach, overlooked by an old lighthouse, is a popular spot for watching the sunset. Inland, tree-lined Mananchira Square, with its musical fountain, surrounds the massive Mananchira Tank, an artificial pond",
    "country" : "India"
}, {
    "id": 5,
    "city": "Idukki",
    "description":"Idukki district is a densely forested, mountainous region in the south Indian state of Kerala. In the north, Anamudi mountain towers over Eravikulam National Park, where the rare, blue Neelakurinji flower blooms every 12 years. Nearby, Munnar is a hill station known for its sprawling tea plantations and Tea Museum. Farther south is the vast, curved Idukki Dam and Periyar National Park, a tiger and elephant reserve.",
    "country" : "India"
}]

// A promo message to user 
var message = "Black Friday! Get 50% cachback on saving your first spot.";

app.get('/messages', function (req, res) {
    res.end(JSON.stringify(message));
})

// Get the list of destinations, convert it to JSON and send it back to client 
app.get('/destination', function (req, res) {
    var count = req.query.count != undefined ? req.query.count : req.query.count = 100;
    if(req.query.country){
        var countrySpots = destinations.filter(function(destination) {
            return destination.country == req.query.country
        });
        res.end(JSON.stringify(countrySpots.slice(0, count)));
    }
    
    res.end(JSON.stringify(destinations.slice(0, count)));
})

// Get one particular Destination using ID 
app.get('/destination/:id', function (req, res) {
    for (var i = 0; i < destinations.length; i++) {
        if(destinations[i].id == req.params.id){
            res.end(JSON.stringify(destinations[i]));
        }
    }
})

// Create a new Destination and add it to existing Destinations list 
app.post('/destination', function (req, res) {
    var newDestination = {
        "city": req.body.city,
        "description": req.body.description,
        "country" : req.body.country,
        "id": index + 1
    }

    index++;

    destinations.push(newDestination);
    res.status(201).end(JSON.stringify(newDestination));
})

// Update a Destination 
app.put('/destination/:id', function (req, res) {
    var destination;
    for (var i = 0; i < destinations.length; i++) {
        if(destinations[i].id == req.params.id){
            destinations[i].city = req.body.city;
            destinations[i].country = req.body.country;
            destinations[i].description = req.body.description;
            destination = destinations[i];
        }
    }

    res.end(JSON.stringify(destination));
})

// Delete a Destination 
app.delete('/destination/:id', function (req, res) {
    for (var i = 0; i < destinations.length; i++) {
        if(destinations[i].id == req.params.id){
            destinations.splice(i, 1);
            res.status(204).end(JSON.stringify(destinations[i]));
        }
    }
});

// Home Page 
app.get('/', (req, res) => res.send('Welcome! You are all set to go!'))

// Configure server 
var server = app.listen(9000, '127.0.0.1', function (req, res) {

    var host = server.address().address
    var port = server.address().port

    console.log(`Server running at http://${host}:${port}/`);
})

