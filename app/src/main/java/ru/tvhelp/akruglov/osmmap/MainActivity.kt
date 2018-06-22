package ru.tvhelp.akruglov.osmmap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.api.IMapController
import org.osmdroid.views.overlay.OverlayItem
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.Marker


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configuration.getInstance().load(applicationContext, PreferenceManager.getDefaultSharedPreferences(applicationContext))
        setContentView(R.layout.activity_main)

        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        val mapController = map.controller
        mapController.setZoom(16.0)
        val startPoint = GeoPoint(55.806271 - 0.001, 37.83485 - 0.001)
        mapController.setCenter(startPoint)

        val overlayPoint = GeoPoint(55.806271,37.83485)

        /*val overlays = arrayListOf<OverlayItem>()
        val item = OverlayItem("Test", "Test description", overlayPoint)
        item.setMarker(ContextCompat.getDrawable(this, R.drawable.ic_lightbulb_outline_24dp))
        overlays.add(item)

        val over = ItemizedIconOverlay<OverlayItem>(this, overlays, object: ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
            override fun onItemLongPress(index: Int, item: OverlayItem?): Boolean {
                return true
            }

            override fun onItemSingleTapUp(index: Int, item: OverlayItem?): Boolean {
                return true
            }
        })

        map.overlays.add(over)*/

        val marker = Marker(map)
        marker.position = overlayPoint
        marker.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_lightbulb_outline_24dp))
        marker.title = "Test"
        map.overlays.add(marker)

        map.invalidate()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}
