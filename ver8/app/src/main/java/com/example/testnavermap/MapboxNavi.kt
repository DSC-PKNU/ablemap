package com.example.testnavermap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.mapboxsdk.style.layers.Property.ICON_ROTATION_ALIGNMENT_VIEWPORT
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import kotlinx.android.synthetic.main.mapbox_navi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import kotlinx.android.synthetic.main.mapbox_navi.*

class MapboxNavi : AppCompatActivity(), OnMapReadyCallback,
    PermissionsListener, MapboxMap.OnMapClickListener {

    private lateinit var mapView: MapView
    private lateinit var map: MapboxMap
    private lateinit var btn: FloatingActionButton
    private var permissionsManager: PermissionsManager = PermissionsManager(this)
    private var currentRoute: DirectionsRoute? = null
    private var originLocation: Location? = null
    private var navigationMapRoute: NavigationMapRoute? = null
    private var symbolManager: SymbolManager? = null



    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Mapbox.getInstance(this, getString(R.string.access_token))
        setContentView(R.layout.mapbox_navi)

        mapView = findViewById<MapView>(R.id.mapbox)
        mapView.getMapAsync(this)
        //map.moveCamera(CameraUpdateFactory.zoomTo(15.0))

        btn = findViewById(R.id.btnStartNavigation)
        btn.setOnClickListener {
            if (currentRoute != null) {
                val navigationLauncherOptions = NavigationLauncherOptions.builder() //1
                    .directionsRoute(currentRoute) //2
                    .shouldSimulateRoute(true) //3
                    .build()
                NavigationLauncher.startNavigation(this, navigationLauncherOptions) //4
            }
        }
    }

    override fun onMapReady(mapboxMap: MapboxMap) {

        mapboxMap.moveCamera(CameraUpdateFactory.zoomTo(9.0))


        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.322452, 129.270128))
                .title("웨이브온 커피")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.272325, 129.253047))
                .title("디원카페")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.15656638557412, 129.17471361759624))
                .title("해운대 미포끝집")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.27218559363052, 129.25304310276127))
                .title("우즈베이커리")
        )
        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.16013190680187, 129.17083039722584))
                .title("해운대 블루라인 파크")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.16205256255908, 129.16039279722594))
                .title("해운대 백년식당")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.158661599223095, 129.16034660606357))
                .title("해운대해수욕장")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.15673324536759, 129.15225788955476))
                .title("더베이101")
        )
        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.12768750403303, 129.09527745489584))
                .title("UN기념공원")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.1295566280063, 129.09407626838936))
                .title("부산박물관")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.13882484839444, 129.06524426838968))
                .title("부산시민회관")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.100697468940155, 129.03261186838844))
                .title("용두산공원")
        )
        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.101816089193505, 129.03377622605936))
                .title("부산영화체험박물관")
        )

        mapboxMap?.addMarker(
            MarkerOptions()
                .position(LatLng(35.101781423572, 129.03365237883227))
                .title("트릭아이 뮤지엄")
        )







        Log.i("Map", "is ready")
        this.map = mapboxMap
        mapboxMap.setStyle(
            Style.Builder().fromUri("mapbox://styles/mapbox/streets-v11")
        ) {
            // Map is set up and the style has loaded. Now you can add data or make other map adjustments
            enableLocationComponent(it)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionResult(granted: Boolean) {
        TODO("Not yet implemented")
    }

    @SuppressWarnings("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Create and customize the LocationComponent's options
            val customLocationComponentOptions = LocationComponentOptions.builder(this)
                .trackingGesturesManagement(true)
                .accuracyColor(ContextCompat.getColor(this, R.color.colorGreen))
                .build()

            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(
                this,
                loadedMapStyle
            )
                .locationComponentOptions(customLocationComponentOptions)
                .build()

            // Setup map listener
            map.addOnMapClickListener(this)

            // Get an instance of the LocationComponent and then adjust its settings
            map.locationComponent.apply {

                // Activate the LocationComponent with options
                activateLocationComponent(locationComponentActivationOptions)

                // Enable to make the LocationComponent visible
                isLocationComponentEnabled = true

                // Set the LocationComponent's camera mode
                cameraMode = CameraMode.TRACKING

                // Set the LocationComponent's render mode
                renderMode = RenderMode.COMPASS
            }

            // Setup camera position
            val location =  map.locationComponent.lastKnownLocation
            if (location != null) {
                val position = CameraPosition.Builder()
                    .target(LatLng(35.179843895462646, 129.07498699785063))
                    .zoom(25.0) // Sets the zoom
                    .bearing(0.0) // Rotate the camera
                    .tilt(0.0) // Set the camera tilt
                    .build() // Creates a CameraPosition from the builder

                map.animateCamera(
                    CameraUpdateFactory
                        .newCameraPosition(position), 70000
                )
            }

            // Setup the symbol manager object
            symbolManager = SymbolManager(mapView, map, loadedMapStyle)

            // add click listeners if desired
            symbolManager?.addClickListener { symbol ->

            }
            symbolManager?.addLongClickListener { symbol ->

            }
            // set non-data-driven properties, such as:
            symbolManager?.iconAllowOverlap = true
            symbolManager?.iconTranslate = arrayOf(-4f, 5f)
            symbolManager?.iconRotationAlignment = ICON_ROTATION_ALIGNMENT_VIEWPORT

            val bm: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.map_marker_light)
            map.style?.addImage("place-marker", bm)
        }
        else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)
        }
    }

    @SuppressWarnings("MissingPermission")
    override fun onStart() {
        super.onStart()
        mapView.onStart()
        Log.d("State", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        Log.d("State", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        Log.d("State", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        Log.d("State", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        Log.d("State", "onDestroy()")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
        Log.d("State", "onLowMemory()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
        Log.d("State", "onSaveInstanceState()")
    }

    private fun addPlaceMarker(location: LatLng) {
        // Add symbol at specified lat/lon
        val symbol = symbolManager?.create(
            SymbolOptions()
                .withLatLng(location)
                .withIconImage("place-marker")
                .withIconSize(1.0f)
        )
    }

    override fun onMapClick(point: LatLng): Boolean {
        

        addPlaceMarker(point)
        checkLocation()
        originLocation?.run {
            val startPoint = Point.fromLngLat(longitude, latitude)
            val endPoint = Point.fromLngLat(point.longitude, point.latitude)

            getRoute(startPoint, endPoint)
        }
        return false
    }

    fun checkLocation() {
        if (originLocation == null) {
            map.locationComponent.lastKnownLocation?.run {
                originLocation = this
            }
        }
    }

    private fun getRoute(originPoint: Point, endPoint: Point) {
        NavigationRoute.builder(this) //1
            .accessToken(Mapbox.getAccessToken()!!) //2
            .origin(originPoint) //3
            .destination(endPoint) //4
            .build() //5
            .getRoute(object : Callback<DirectionsResponse> { //6
                override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                    Log.d("MainActivity", t.localizedMessage)
                }


                override fun onResponse(
                    call: Call<DirectionsResponse>,
                    response: Response<DirectionsResponse>
                ) {
                    if (navigationMapRoute != null) {
                        navigationMapRoute?.updateRouteVisibilityTo(false)
                    } else {
                        navigationMapRoute = NavigationMapRoute(null, mapView, map)
                    }

                    currentRoute = response.body()?.routes()?.first()
                    if (currentRoute != null) {
                        navigationMapRoute?.addRoute(currentRoute)
                    }

                    btnStartNavigation.isEnabled = true
                }
            })
    }
}