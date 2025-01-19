package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculator.databinding.ActivityMainBinding
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalculateTip.setOnClickListener {
            calculate_tip()
        }
    }
    private fun calculate_tip() {
        val cost = binding.inputServiceCost.text.toString().toIntOrNull()
        val opt = binding.radiogroupRating.checkedRadioButtonId
        val rate = when(opt){
            binding.radiobtnAmazing.id -> 0.2
            binding.radiobtnGood.id -> 0.15
            else -> 0.1
        }
        var tip_amount = (cost ?: 0) * rate

        if (binding.switchRoundup.isChecked){
            tip_amount = round(tip_amount)
        }
        binding.txtTipAmount.setText(tip_amount.toString())
    }
}


