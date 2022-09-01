package silbajuk.ch8.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import silbajuk.ch8.beatbox.databinding.ActivityMainBinding
import silbajuk.ch8.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beatBox = BeatBox(assets)

        val binding:ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply{
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }

        binding.speedSeekBar.apply {
            setOnSeekBarChangeListener(SeekBarListener())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    private inner class SeekBarListener:SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            beatBox.setSpeed(p1)
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
            //TODO("Not yet implemented")
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
            //TODO("Not yet implemented")
        }

    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding): RecyclerView.ViewHolder(binding.root){
        init{
            binding.viewModel = SoundViewModel(beatBox)
        }

        fun bind(sound:Sound){
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    private inner class SoundAdapter(private val sounds:List<Sound>): RecyclerView.Adapter<SoundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

        override fun getItemCount() = sounds.size
    }

}