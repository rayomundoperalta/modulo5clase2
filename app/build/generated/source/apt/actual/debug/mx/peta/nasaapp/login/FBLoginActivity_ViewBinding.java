// Generated code from Butter Knife. Do not modify!
package mx.peta.nasaapp.login;

import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.facebook.login.widget.LoginButton;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import mx.peta.nasaapp.R;

public class FBLoginActivity_ViewBinding<T extends FBLoginActivity> implements Unbinder {
  protected T target;

  public FBLoginActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.loginButton = finder.findRequiredViewAsType(source, R.id.fb_login_button, "field 'loginButton'", LoginButton.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loginButton = null;

    this.target = null;
  }
}
