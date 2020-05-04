package com.mosh.testgloballogic.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mosh.testgloballogic.R
import com.mosh.testgloballogic.databinding.LatopDetailActivityBinding
import com.mosh.testgloballogic.domain.LatopItem

class LatopDetailActivity : AppCompatActivity() {

    private lateinit var binding: LatopDetailActivityBinding
    private lateinit var latopItem: LatopItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LatopDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        obtainLatopValue()
        initView()
    }

    private fun obtainLatopValue() {
        latopItem = intent.getSerializableExtra("LATOP") as LatopItem
    }

    private fun initView() {
        supportActionBar?.title = latopItem.title
        binding.tvDescription.text = latopItem.description

        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions()
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_default))
            .load(latopItem.image)
            .into(binding.ivImageLatop)
    }
}
