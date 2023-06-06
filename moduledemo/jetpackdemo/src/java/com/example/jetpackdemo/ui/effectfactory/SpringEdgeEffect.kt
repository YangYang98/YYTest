package java.com.example.jetpackdemo.ui.effectfactory

import android.widget.EdgeEffect
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView


/**
 * Create by Yang Yang on 2023/6/6
 */
class SpringEdgeEffect : RecyclerView.EdgeEffectFactory() {

    override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
        return object : EdgeEffect(view.context) {

            override fun onPull(deltaDistance: Float) {
                super.onPull(deltaDistance)
                handlePull(deltaDistance)
            }

            override fun onPull(deltaDistance: Float, displacement: Float) {
                super.onPull(deltaDistance, displacement)
                handlePull(deltaDistance)
            }
            //更新recyclerView的每一个可见item的`translationY`值
            private fun handlePull(deltaDistance: Float) {
                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1

                val translationYDelta = sign * view.height * deltaDistance * 0.4f
                view.forEach {
                    if (it.isVisible) {
                        view.getChildViewHolder(it).itemView.translationY += translationYDelta
                    }
                }

            }

            ////在这里让recyclerView的每一个可见item的translationY值变成0
            override fun onRelease() {
                super.onRelease()

                view.forEach {
                    /*val animator = ValueAnimator.ofFloat(view.getChildViewHolder(it).itemView.translationY, 0f).setDuration(500)
                    animator.interpolator = DecelerateInterpolator(2.0f)
                    animator.addUpdateListener { valueAnimator ->
                        view.getChildViewHolder(it).itemView.translationY = valueAnimator.animatedValue as Float
                    }
                    animator.start()*/

                    val animator = SpringAnimation(
                        it, SpringAnimation.TRANSLATION_Y
                    ).setSpring(
                        SpringForce().setFinalPosition(0f)
                            .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                            .setStiffness(SpringForce.STIFFNESS_LOW)
                    )

                    animator.start()

                }
            }

            override fun onAbsorb(velocity: Int) {
                super.onAbsorb(velocity)

                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                val translationVelocity = sign * velocity * 0.4f

                view.forEach {
                    if (it.isVisible) {

                    }
                }
            }
        }
    }
}