
// The svg
var svg = d3.select("svg"),
    width = +svg.attr("width"),
    height = +svg.attr("height");

// Map and projection
var path = d3.geoPath();
var projection = d3.geoMercator()
    .scale(110000)
    .center([-72.5904,-38.7359])
    .translate([width / 2, height / 2]);

// Data and color scale
var data = d3.map();
var colorScale = d3.scaleThreshold()
    .domain([10, 20, 300, 400, 500, 600])
    .range(d3.schemeBlues[7]);

// Load external data and boot
d3.queue()
    .defer(d3.json, "../data/map.geojson")
    .defer(d3.csv, "../data/Registrostest.csv", function(d) { data.set(d.code, +d.pop); })
    .await(ready);

function ready(error, topo) {

    // Draw the map
    svg.append("g")
        .selectAll("path")
        .data(topo.features)
        .enter()
        .append("path")
        // draw each country
        .attr("d", d3.geoPath()
            .projection(projection)
        )
        // set the color of each country
        .attr("fill", function (d) {
            d.total = data.get(d.id) || 0;
            return colorScale(d.total);
        });
}
