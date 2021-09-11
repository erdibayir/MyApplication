package com.elifersumer.myapplication.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.elifersumer.myapplication.MessageEvent
import com.elifersumer.myapplication.R
import kotlinx.android.synthetic.main.fragment_emirgiris.*
import kotlinx.android.synthetic.main.fragment_emirgiris.view.*
import kotlinx.android.synthetic.main.fragment_piyasa.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class EmirFragment : Fragment() {
    lateinit var messageTextView: EditText
    lateinit var alisbtn : RadioButton
    lateinit var satisbtn: RadioButton
    lateinit var tamamBtn: Button
    lateinit var adet: EditText
    lateinit var hisseler: AutoCompleteTextView
    lateinit var rg: RadioGroup
    var messageToDisplay:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_emirgiris, container, false)
        val args = this.arguments
        val satisData = args?.get("satis")
        val alisData = args?.get("alis")
        val isimData = args?.get("isim")
        val alisOrSatis = args?.get("alisOrSatis")
        Log.d("satis:",satisData.toString())

        alisbtn = view.findViewById(R.id.alisbtn) as RadioButton
        satisbtn = view.findViewById(R.id.satisbtn) as RadioButton
        tamamBtn = view.findViewById(R.id.emirTamamButton) as Button

        val incrementBtn = view.findViewById(R.id.incrementFiyat) as Button
        val decrementBtn = view.findViewById(R.id.decrementFiyat) as Button

        adet = view.findViewById(R.id.edtxt_adet) as EditText

        val alisFiyat = view.findViewById(R.id.val_alis) as TextView
        val satisFiyat = view.findViewById(R.id.val_satis) as TextView
        val fiyat = view.findViewById(R.id.edtxt_fiyat) as EditText

        alisFiyat.text = alisData.toString()
        satisFiyat.text = satisData.toString()

        rg = view.findViewById(R.id.daily_weekly_button_view) as RadioGroup

        hisseler = view.findViewById(R.id.autoCompleteTextView) as AutoCompleteTextView
        hisseler.setText(isimData.toString())
        if (alisOrSatis == "satis"){
            fiyat.setText(alisData.toString())
            satisbtn.isChecked = true
        }
        else{
            fiyat.setText(satisData.toString())
            alisbtn.isChecked = true
        }

        alisbtn.setOnClickListener(View.OnClickListener {
            //Toast.makeText(this@EmirFragment.requireActivity(),"AŞKSIN ", Toast.LENGTH_SHORT).show()
            alisbtn.textSize = 16.toFloat()
            //alisbtn.setTextColor(Color.parseColor("#f5f5f5"))
            alisbtn.setTypeface(null, Typeface.BOLD)


            satisbtn.textSize = 15.toFloat()
            satisbtn.setTypeface(null, Typeface.NORMAL)


            fiyat.setText(satisFiyat.text)
        })

        satisbtn.setOnClickListener(View.OnClickListener {

            satisbtn.textSize = 16.toFloat()
            //alisbtn.setTextColor(Color.parseColor("#f5f5f5"))
            satisbtn.setTypeface(null, Typeface.BOLD)

            alisbtn.textSize = 15.toFloat()
            alisbtn.setTypeface(null, Typeface.NORMAL)

            fiyat.setText(alisFiyat.text)
        })


        incrementBtn.setOnClickListener(View.OnClickListener {
            fiyat.setText((fiyat.text.toString().toFloat() + 1).toString())
        })

        decrementBtn.setOnClickListener(View.OnClickListener {

            if(fiyat.text.toString().toFloat() > 1){
                fiyat.setText((fiyat.text.toString().toFloat() - 1).toString())
            }
            else{
                val x = 0
                fiyat.setText(x.toString())
            }
        })


        var input_islem_tipi = "Alış"
        rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{radioGroup, i ->
            input_islem_tipi = ""
            when(i) {
                R.id.alisbtn -> { input_islem_tipi = "Alış" }
                R.id.satisbtn -> { input_islem_tipi = "Satış" }
            }
        })

        tamamBtn.setOnClickListener {
            if(view.findViewById<EditText>(R.id.edtxt_adet).text.toString() == ""){
                Toast.makeText(this@EmirFragment.requireActivity(),"Lütfen adet giriniz!", Toast.LENGTH_SHORT).show()
            }else {

                val input_isim = hisseler.text.toString()
                val input_fiyat = fiyat.text.toString()
                val input_adet = adet.text.toString()


                /*
                // bu alana direkt girildiğinde null yazmayacak o kısımları default olarak bazı bilgilere ayarla,
                //!!!!! kullanıcının parası yetecek mi kontrolünü de unutma, fiyatı adetle carpmayı unutma(hatta o çarpılmış halinin değerini ekranda gösterirsek iyi olur)
                // bu miktar zımbırtısı var, o da kullanıcının aldığı adet kadar arttırılabilir
                //işlem limiti yazan kullanıcının yatırım hesabındaki parası mı??
                if( miktardayazan fiyat == hissenin şuanki fiyatı){
                    - bilgileri direkt olarak gerçekleşen emirler kısmına aktar
                    - kullanıcı profili için yapılacak değişiklikler falan(para miktarının azalması, portföyüne o bilginin gitmesi) ayarla
                }
                else{
                    - aynı işlemler fakat bekleyen emirlerime gitsin
                }
                 */
                //database olcak
                /* BekleyenEmirlerimData(
                     input_isim,
                     input_adet,
                     input_fiyat,
                     input_islem_tipi,
                     "İptal",
                     "Değiş"
                 )*/

            }
        }


        val hisseler = resources.getStringArray(R.array.hisseler)
        val fiyatTipleri = resources.getStringArray(R.array.fiyatTipleri)
        val sureTipleri = resources.getStringArray(R.array.sureTipleri)
        var arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item, hisseler)
        var arrayAdapter2 = ArrayAdapter(requireContext(),R.layout.dropdown_item, fiyatTipleri)
        var arrayAdapter3 = ArrayAdapter(requireContext(), R.layout.dropdown_item, sureTipleri)

        view.autoCompleteTextView.setAdapter(arrayAdapter)
        view.autoCompleteTextViewFiyatTipi.setAdapter(arrayAdapter2)
        view.autoCompleteTextViewSureTipi.setAdapter(arrayAdapter3)


        // Inflate the layout for this fragment
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event:MessageEvent){
        messageToDisplay = event.message
        Log.d("deneme1:",event.message)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}