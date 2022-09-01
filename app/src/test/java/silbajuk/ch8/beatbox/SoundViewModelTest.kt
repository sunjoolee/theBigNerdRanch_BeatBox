package silbajuk.ch8.beatbox

import org.hamcrest.core.Is.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {

    private lateinit var sound:Sound
    private lateinit var subject: SoundViewModel
    private lateinit var beatBox: BeatBox

    @Before
    fun setUp() {
        beatBox = mock(BeatBox::class.java)

        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    //SoundViewModel의 title 속성값이 Sound의 name 속성값과 일치하는지 검사
    @Test
    fun exposesSoundNameAsTitle(){
        MatcherAssert.assertThat(subject.title, `is`(sound.name))
    }

    //SoundViewModel과 BeatBox.play(Sound) 함수가 잘 연동되는지 검사
    @Test
    fun callsBeatBoxPlayOnButtonCLicked(){
        subject.onButtonClicked()

        verify(beatBox).play(sound)
    }

}