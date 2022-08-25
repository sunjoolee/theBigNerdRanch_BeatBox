package silbajuk.ch8.beatbox

import android.content.res.AssetManager
import android.util.Log
import java.lang.Exception

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {
    fun loadSounds():List<String>{
        try{
            val soundNames = assets.list(SOUNDS_FOLDER)!!
            Log.d(TAG, "Found ${soundNames.size} sounds")
            return soundNames.asList()
        }catch(e:Exception){
            Log.e(TAG, "Could not lst assets", e)
            return emptyList()
        }
    }
}