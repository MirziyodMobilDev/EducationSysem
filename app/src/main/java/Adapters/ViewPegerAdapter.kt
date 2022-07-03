package Adapters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.educationsysem.NewGroupAddFragment
import com.example.educationsysem.OpenedFragment

class ViewPegerAdapter( var title:ArrayList<String>,  fragmentManager: FragmentManager,var titleGroup:String): FragmentStatePagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                var bundle = Bundle()
                bundle.putString("title",titleGroup)
                var openedFragment = OpenedFragment()
                openedFragment.arguments = bundle
               return openedFragment
            }

           1 -> {

               var bundle = Bundle()
               bundle.putString("title",titleGroup)
               var newGroupAddFragment = NewGroupAddFragment()
               newGroupAddFragment.arguments = bundle
               return newGroupAddFragment
            }
            else ->{
                return Fragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}