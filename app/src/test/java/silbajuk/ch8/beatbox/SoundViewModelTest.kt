package silbajuk.ch8.beatbox

import org.hamcrest.core.Is.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class SoundViewModelTest {

    private lateinit var sound:Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        sound = Sound("assetPath")
        subject = SoundViewModel()
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle(){
        MatcherAssert.assertThat(subject.title, `is`(sound.name))
    }
}