var World = {

	markerDrawable_idle: null,
	markerDrawable_selected: null,
	markerDrawable_directionIndicator: null,

	markerList: [],

	// called to inject new POI data
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {

		AR.context.destroyAll();

		World.markerDrawable_idle = new AR.ImageResource("assets/marker_idle.png"),
		World.markerDrawable_selected = new AR.ImageResource("assets/marker_selected.png"),
		World.markerDrawable_directionIndicator = new AR.ImageResource("assets/indi.png"),

		document.getElementById("statusElement").innerHTML = 'Loading JSON objects of Restaurents';

		for (var i = 0; i < poiData.length; i++) {

			var singlePoi = {
				"id": poiData[i].id,
				"latitude": parseFloat(poiData[i].latitude),
				"longitude": parseFloat(poiData[i].longitude),
				"altitude": parseFloat(poiData[i].altitude),
				"title": poiData[i].name,
				"description": poiData[i].description
			};

			World.markerList.push(new Marker(singlePoi));
		}

		document.getElementById("statusElement").innerHTML = 'Loaded JSON Objects containing restaurants ';

	},

	//  user's latest known location, accessible via userLocation.latitude, userLocation.longitude, userLocation.altitude
	userLocation: null,

	// location updates
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {
		World.userLocation = {
			'latitude': lat,
			'longitude': lon,
			'altitude': alt,
			'accuracy': acc
		};
	},

	onMarkerSelected: function onMarkerSelectedFn(marker) {
		// notify native environment
		document.location = "architectsdk://markerselected?id=" + marker.poiData.id;
	}

};

/* forward locationChanges to custom function */
AR.context.onLocationChanged = World.locationChanged;