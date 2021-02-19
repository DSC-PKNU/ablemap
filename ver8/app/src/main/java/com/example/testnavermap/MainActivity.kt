package com.example.testnavermap


import android.content.Intent
import android.graphics.Color
import android.graphics.Color.GRAY
import android.graphics.PointF
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat.*
import com.example.testnavermap.api.DirectionsResponse
import com.example.testnavermap.database.SpotTable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.NaverMap.OnMapClickListener
import com.naver.maps.map.overlay.*
import com.naver.maps.map.overlay.InfoWindow.DefaultViewAdapter
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.naver.maps.map.util.MarkerIcons.GRAY
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var locationSource: FusedLocationSource
    private lateinit var map: NaverMap


    protected var spotTable: SpotTable? = null
    protected var spots = ArrayList<Spot>()
    private val k = 0

    private val markers = arrayOfNulls<Marker>(16)

    private var infoWindow: InfoWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        val fbn = findViewById<View>(R.id.myLocation_btn) as FloatingActionButton
        fbn.setOnClickListener {
            val Bintent = Intent(this, MapboxNavi::class.java)//applicationContext
            startActivity(Bintent)
            //finish()
        }


        //intent
        val Search: Button = findViewById(R.id.main_btn_search)

        Search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(START)
        }

        naviView.setNavigationItemSelectedListener(this)  //네비게이션 메뉴 아이템에 클릭 속성 부여

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)

        spotTable = SpotTable.instance(applicationContext)

        if (spotTable!!.isEmpty())
            insertToDB()

        spots = spotTable?.loadByTable() as ArrayList<Spot>
    }
//주차,휠체어입구,장애인화장실
    private fun insertToDB() {
        spotTable?.insert(
                "웨이브온 커피", "부산 기장군 장안읍 해맞이로 286 웨이브온커피", "waveon",
                "http://naver.me/G0fCKbmK", "",
            1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0

        )
        spotTable?.insert(
                "우즈 베이커리", "부산 기장군 일광면 일광로 346", "wooz", "http://naver.me/5KZ9NXeo", "",
            0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0
        )
        spotTable?.insert(
                "해운대 블루라인파크", "부산 해운대구 청사포로 116", "blueline", "http://naver.me/G3J5iqKj", ""
        )
        spotTable?.insert(
                "해운대 미포끝집", "부산 해운대구 달맞이길62번길 77", "mipo", "http://naver.me/GqBUPabH", ""
        )
        spotTable?.insert(
                "해운대 백년식당", "부산 해운대구 구남로 23", "rest100", "http://naver.me/FjoE40Oo", ""
        )
        spotTable?.insert(
                "더베이 101",
                "부산 해운대구 동백로 52",
                "thebay101",
                "http://naver.me/GrSzXRaK",
                "http://m.greentrip.kr/Mobile/Board/BfTravelInfo/BoardView.aspx?PageNo=4&Search=&SearchString=&Seq=2089",
                1,
                1,
                0,
                0,
                0,
                0,
                1,
                1,
                1,
                0,
                0
        )
        spotTable?.insert(
                "트릭아이 뮤지엄",
                "부산 중구 대청로126번길 12 부산영화체험박물관",
                "trick",
                "http://naver.me/x9JKCymJ",
                "http://m.greentrip.kr/Mobile/Board/BfTravelInfo/BoardView.aspx?PageNo=5&Search=&SearchString=&Seq=1752",
                0,
                1,
                0,
                1,
                0,
                1,
                1,
                1,
                0,
                0,
                1
        )
        spotTable?.insert(
                "부산 영화 체험 박물관",
                "부산 중구 대청로126번길 12", "busanmovie",
                "http://naver.me/xNdOTHU0",
                "http://m.greentrip.kr/Mobile/Board/BfTravelInfo/BoardView.aspx?PageNo=7&Search=&SearchString=&Seq=623",
                1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0
        )
        spotTable?.insert(
                "부산 근대 역사관", "부산 중구 대청로 104", "history",
                "http://naver.me/FAr6Dd51",
                "http://m.greentrip.kr/Mobile/Board/BfTravelInfo/BoardView.aspx?PageNo=3&Search=&SearchString=&Seq=2163",
                1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0
        )
        spotTable?.insert(
                "용두산공원", "부산 중구 용두산길 37-55", "yongdu",
                "http://naver.me/xjefpaY8",
                "http://m.greentrip.kr/Mobile/Board/BfTravelInfo/BoardView.aspx?PageNo=1&Search=PlaceName&SearchString=%ec%9a%a9%eb%91%90%ec%82%b0&Seq=2107",
                0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1
        )
        spotTable?.insert(
                "UN기념공원", "부산 남구 유엔평화로 93", "unpark",
                "http://naver.me/xzcVsgHk",
                "http://m.greentrip.kr/Mobile/Board/BfTravelInfo/BoardView.aspx?PageNo=1&Search=PlaceName&SearchString=un&Seq=485",
                1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0
        )
        spotTable?.insert(
                "부산박물관", "부산 남구 유엔평화로 63 부산광역시시립박물관", "busanmuseum", "http://naver.me/FiLNBAHY", ""
        )
        spotTable?.insert(
                "부산 시민회관", "부산 동구 자성로133번길 16 부산시민회관", "simin", "http://naver.me/xB4GENwi", ""
        )
        spotTable?.insert(
                "해운대 해수욕장", "부산 해운대구 우동", "haesu",
                "http://naver.me/xmrks1pn",
                "http://m.greentrip.kr/Mobile/Board/BfTravelInfo/BoardView.aspx?PageNo=6&Search=&SearchString=&Seq=849",
                1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1
        )
        spotTable?.insert(
                "씨라이프 부산 아쿠아리움", "부산 해운대구 해운대해변로 266", "aqua", "http://naver.me/xM2k71LQ", ""
        )
        spotTable?.insert(
                "부산 시립미술관", "부산 해운대구 APEC로 58 부산시립미술관", "gallery", "http://naver.me/5RcTsq3l", ""
        )
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {        //네비게이션 메뉴 아이템 클릭 시 수행
        when (item.itemId) {
            R.id.hae -> {
                val intent = Intent(this, Haeundae_info::class.java)
                startActivity(intent)
            }
            R.id.nam -> {
                val intent = Intent(this, Nampo_info::class.java)
                startActivity(intent)
            }
            R.id.yeong -> {
                val intent = Intent(this, Yeongdo_info::class.java)
                startActivity(intent)
            }
            R.id.mus -> {
                val intent = Intent(this, Museum_info::class.java)
                startActivity(intent)
            }
            R.id.nat -> {
                val intent = Intent(this, Nature_info::class.java)
                startActivity(intent)
            }
            R.id.cafe -> {
                val intent = Intent(this, Cafe_info::class.java)
                startActivity(intent)
            }
        }
        layout_drawer.closeDrawers()  //네비게이션 뷰 닫기
        return false
    }

    override fun onBackPressed() {
        if (layout_drawer.isDrawerOpen(START)) {
            layout_drawer.closeDrawers()
        } else {
            super.onBackPressed()    // 일반 백버튼 기능 실행
        }
    }

    @UiThread
    @Override
    override fun onMapReady(naverMap: NaverMap) {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.locationTrackingMode = LocationTrackingMode.Face
        val uiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = false
        uiSettings.isScaleBarEnabled=false

        for (i in 0..15) {
            markers[i] = Marker()
            markers[i]!!.icon = MarkerIcons.GRAY
            markers[i]!!.tag = spots.get(i)
        }

        markers[0]!!.position = LatLng(35.322452, 129.270128)
        markers[1]!!.position = LatLng(35.272325, 129.253047)
        markers[2]!!.position = LatLng(35.160193, 129.170820)
        markers[3]!!.position = LatLng(35.156733, 129.174748)
        markers[4]!!.position = LatLng(35.162132, 129.160361)
        markers[5]!!.position = LatLng(35.156784, 129.152138)
        markers[6]!!.position = LatLng(35.101962, 129.033688)
        markers[7]!!.position = LatLng(35.101895, 129.033776)
        markers[8]!!.position = LatLng(35.102841, 129.031203)
        markers[9]!!.position = LatLng(35.100689, 129.032633)
        markers[10]!!.position = LatLng(35.128027, 129.096134)
        markers[11]!!.position = LatLng(35.129625, 129.094148)
        markers[12]!!.position = LatLng(35.127267, 129.093665)
        markers[13]!!.position = LatLng(35.158717, 129.160447)
        markers[14]!!.position = LatLng(35.159354, 129.161001)
        markers[15]!!.position = LatLng(35.166772, 129.137073)


        naverMap.setContentPadding(0, 0, 0, 500)

        infoWindow = InfoWindow()

        naverMap.onMapClickListener =
            OnMapClickListener { coord: PointF?, point: LatLng? -> infoWindow!!.close() }

        val listener = Overlay.OnClickListener { overlay: Overlay ->
            val marker = overlay as Marker
            if (marker.infoWindow == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                infoWindow!!.open(marker)
            } else {
                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                infoWindow!!.close()
            }
            true
        }

        infoWindow!!.adapter = object : DefaultViewAdapter(this) {
            override fun getContentView(infoWindow: InfoWindow): View {
                val marker = infoWindow.marker
                val spot = marker!!.tag as Spot?
                val view = View.inflate(this@MainActivity, R.layout.simple_info, null)
                (view.findViewById<View>(R.id.name2) as TextView).text = spot!!.name
                (view.findViewById<View>(R.id.imageView25) as ImageView).setImageResource(
                        resources.getIdentifier(
                                spot!!.image,
                                "drawable",
                                packageName
                        )
                )
                return view
            }

            fun getText(infoWindow: InfoWindow): CharSequence {
                val summary = infoWindow.tag as? DirectionsResponse.Route.Summary ?: return ""
                return "${summary.distance}m	/	${summary.duration / 1000 / 60}분"
            }
        }

        infoWindow!!.onClickListener = Overlay.OnClickListener { overlay: Overlay? ->
            val marker = infoWindow!!.marker
            val spot = marker!!.tag as Spot?
            val intent = Intent(this@MainActivity, DetailView::class.java)
            intent.putExtra("spot", spot)
            startActivity(intent)
            true
        }

        for (j in 0..13) {
            markers[j]!!.map = naverMap
            markers[j]!!.onClickListener = listener
        }

        val path = PathOverlay().apply {
            width = resources.getDimensionPixelSize(R.dimen.overlay_line_width)
            color = ResourcesCompat.getColor(resources, R.color.line_color, theme)
            outlineColor = ResourcesCompat.getColor(resources, R.color.outline_color, theme)
            patternImage = OverlayImage.fromResource(R.drawable.path_pattern)

        }


        val polyline = PolylineOverlay().apply {
            width = resources.getDimensionPixelSize(R.dimen.overlay_line_width)
            color = ResourcesCompat.getColor(resources, R.color.line_color, theme)
        }


    }
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }


}
    /*fun showOverlays(coord: LatLng, caption: String? = null)	{
        val marker = Marker()
        marker.apply {
            if	(caption	==	null)	{
                captionText =""
                lifecycleScope.launch {
                    captionText =	MapsApi.reverseGeocode(coord).toString()
                }
            }	else	{
                captionText =	caption
            }
       path.map =	null
       infoWindow?.close()


       //marker.map = naverMap
        if	(naverMap!!.locationOverlay.isVisible)	{
            polyline.apply {
                coords =	listOf(naverMap.locationOverlay.position,	coord)
                map	=	naverMap
            }
           /* lifecycleScope.launch {
                MapsApi.directions(naverMap.locationOverlay.position, coord).MapsApi.firstRoute?.let	{
                    path.coords = it.coords
                    path.map =	naverMap
                    infoWindow!!. tag =	it.summary
                    infoWindow!!. invalidate()
                    infoWindow!!.open(marker)
                }
            }*/
            path.apply {
                coords =	listOf(naverMap.locationOverlay.position,	coord)
                map	=	naverMap
            }
            infoWindow?.apply {
                tag	=
                    "${naverMap.locationOverlay.position.distanceTo(marker.position).roundToInt()}m"
                invalidate()
                open(marker)
            }
        }

    }
   /* */




}

private fun MapsApi.directions(start: LatLng, goal: LatLng) {

}
*/

