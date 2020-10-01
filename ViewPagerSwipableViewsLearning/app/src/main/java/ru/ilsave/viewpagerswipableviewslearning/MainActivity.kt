package ru.ilsave.viewpagerswipableviewslearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4
        )

        val adapter = ViewPagerAdapter(images)
        viewPager.adapter = adapter

     //   viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        //делаем подсказку к действию (он за нас листнет вправо)
//        viewPager.beginFakeDrag()
//        viewPager.fakeDragBy(-7f)
//        viewPager.endFakeDrag()

        tabLayout?.apply {
            TabLayoutMediator(tabLayout , viewPager) { tab, position ->
                tab.text = "Tab $position"
            }.attach()
        }

    }
}