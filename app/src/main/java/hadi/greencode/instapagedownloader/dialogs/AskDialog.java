package hadi.greencode.instapagedownloader.dialogs;

import android.content.Context;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.utils.BasedDialog;

public class AskDialog extends BasedDialog {


    @BindView(R.id.btnYes)
    AppCompatButton btnYes;

    @BindView(R.id.btnNo)
    AppCompatButton btnNo;

    @BindView(R.id.tvNote)
    AppCompatTextView tvNote;
    /*
     *   Main
     * */
    private OnDialog onDialog;

    public interface OnDialog {
        void onYesClicked();
    }

    public AskDialog(Context context, String question, OnDialog onDialog) {
        super(context, R.layout.dialog_ask, true, true, true);
        this.onDialog = onDialog;
        getWindow().getAttributes().windowAnimations = R.style.DialogFadeAnimation;
        ButterKnife.bind(this, this);

        if (!TextUtils.isEmpty(question)) {
            tvNote.setText(question);
        }
    }

    @OnClick(R.id.btnYes)
    public void onBtnYesClicked() {
        cancel();
        onDialog.onYesClicked();
    }

    @OnClick(R.id.btnNo)
    public void onBtnNoClicked() {
        cancel();
    }
}

