package com.example.tourismapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.navigation.dropin.NavigationView
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineOptions
import com.mapbox.navigation.ui.maps.route.line.model.RouteLineColorResources
import com.mapbox.navigation.ui.maps.route.line.model.RouteLineResources

class Routes: AppCompatActivity() {
    private lateinit var navigationView: NavigationView

    private val routeLineOption: MapboxRouteLineOptions by lazy {
        MapboxRouteLineOptions.Builder(this)
            .withRouteLineResources(
                RouteLineResources.Builder()
                    .routeLineColorResources(
                        RouteLineColorResources.Builder()
                            .routeDefaultColor(Color.RED)
                            .routeCasingColor(Color.parseColor("#7c3aed"))
                            .build()
                    )
                    .build()
            )
            .withRouteLineBelowLayerId("road-label")
            .withVanishingRouteLineEnabled(true)
            .displaySoftGradientForTraffic(true)
            .softGradientTransition(30)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Main)
        setContentView(R.layout.activity_routes)

        navigationView = findViewById(R.id.navigationView)

        navigationView.api.routeReplayEnabled(true)

        navigationView.customizeViewOptions {
            mapStyleUriNight = Style.DARK
            routeLineOptions = this@Routes.routeLineOption
        }

        navigationView.customizeViewStyles{
            infoPanelBackground = R.drawable.mapbox_bg_custom_info_panel
            tripProgressStyle = R.style.MyCustomTripProgressStyle
            speedLimitStyle = R.style.MyCustomSpeedLimitStyle
            startNavigationButtonStyle = R.style.MyCustomStartNavigationButton
            destinationMarkerAnnotationOptions = PointAnnotationOptions().apply {
                withIconImage(
                    ContextCompat.getDrawable(
                        this@Routes,
                        R.drawable.location_primary
                    )!!.toBitmap()
                )
            }
        }
    }
}
