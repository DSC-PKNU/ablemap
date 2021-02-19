package com.example.testnavermap.api

import androidx.core.content.res.ResourcesCompat
import com.example.testnavermap.R
import com.example.testnavermap.api.MapsApi.directions
import com.google.android.gms.maps.model.LatLng
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PathOverlay
//import com.naver.maps.geometry.LatLng
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DirectionsResponse(
        val route: Map<String, List<Route>>
) {
    @JsonClass(generateAdapter = true)
    class Route(
            val summary: Summary,
            val path: List<List<Double>>
    ) {
        @JsonClass(generateAdapter = true)
        class Summary(
            val distance: Int,
            val duration: Long
        )

        val coords
            get() = path.map { LatLng(it[1], it[0]) }
    }


    val firstRoute
        get() = route.asIterable().firstOrNull()?.value?.firstOrNull()


    fun getText(infoWindow: InfoWindow): CharSequence {
        val summary	=	infoWindow.tag as? DirectionsResponse.Route.Summary ?: return ""
        return	"${summary.distance}m	/	${summary.duration /	1000	/	60}분"
    }


}
/*
val path=	PathOverlay().apply	{
    width	=	resources.getDimensionPixelSize(R.dimen.overlay_line_width)
    color	=	ResourcesCompat.getColor(resources,	R.color.line_color,	theme)
    outlineColor =	ResourcesCompat.getColor(resources,	R.color.outline_color,	theme)
    patternImage =	OverlayImage.fromResource(R.drawable.path_pattern)
}
val infoWindow=	InfoWindow().apply	{
    adapter	=	object	: InfoWindow.DefaultTextAdapter(context)	{
        override	fun	getText(infoWindow: InfoWindow): CharSequence {
            val summary	=	infoWindow.tag as? DirectionsResponse.Route.Summary ?: return	""
            return	"${summary.distance}m	/	${summary.duration /	1000	/	60}분"
        }
    }
}

fun	showOverlays(coord: LatLng,	caption: String? =	null)	{
//	...

    path.map =	null
    infoWindow.close()
    if	(naverMap.locationOverlay.isVisible)	{
        lifecycleScope.launch {
            directions(naverMap.locationOverlay.position,coord).firstRoute?.let	{
                it.coords.also { path.coords = it }
                path.map =	naverMap
                infoWindow.tag =	it.summary
                infoWindow.invalidate()
                infoWindow.open(marker)
            }
        }
    }

}*/
