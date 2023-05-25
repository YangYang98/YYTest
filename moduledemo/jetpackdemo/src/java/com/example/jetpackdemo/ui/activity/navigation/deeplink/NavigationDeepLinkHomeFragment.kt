package java.com.example.jetpackdemo.ui.activity.navigation.deeplink

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.jetpackdemo.R
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.PendingIntent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


/**
 * Create by Yang Yang on 2023/4/28
 */
class NavigationDeepLinkHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_deep_link_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val button = view?.findViewById<Button>(R.id.button_home)
        button?.setOnClickListener {
            sendNotification()
        }

    }

    private fun sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(activity?.packageName, "channel", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "channel description"
            val notificationManager = activity?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        val notification = activity?.let {
            activity?.packageName?.let { it1 ->
                NotificationCompat.Builder(it, it1)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Deep Link")
                    .setContentText("Deep Link")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(getPendingIntent())
                    .build()
            }
        }

        if (notification != null) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            NotificationManagerCompat.from(requireActivity()).notify(1, notification)
        }
    }

    private fun getPendingIntent(): PendingIntent? {
        val args = NavigationDeepLinkHomeFragmentArgs.Builder().build().toBundle()

        return Navigation.findNavController(requireActivity(), R.id.button_home)
            .createDeepLink()
            .setGraph(R.navigation.nav_graph_nav_deep_link)
            .setDestination(R.id.navDeepLinkDetailFragment)
            .setArguments(args)
            .createPendingIntent()
    }
}