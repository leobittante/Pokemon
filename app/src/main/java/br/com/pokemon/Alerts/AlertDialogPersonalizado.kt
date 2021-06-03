package br.com.pokemon.Alerts

import android.app.AlertDialog
import android.content.Context
import br.com.pokemon.R

class AlertDialogPersonalizado {

    private var dialog: AlertDialog? = null

    //NOSSA CLASSE DE ALERTAS
    //RECEBEMOS UM TITULO, UMA MENSAGEM, E UM CONTEXT E RETORNAMOS UM ALERT DIALOG
    fun show(titulo: Int, mensagem: Int, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titulo)
        builder.setMessage(mensagem)
        builder.setOnDismissListener { }
        builder.setPositiveButton(
            "OK"
        ) { arg0, arg1 -> dialog!!.dismiss() }
        dialog = builder.create()
        dialog?.show()
        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)
            ?.setTextColor(context.resources.getColor(R.color.cherry_red))
    }


}