// Generated code from Butter Knife. Do not modify!
package mx.peta.nasaapp;

import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DetailActivity_ViewBinding<T extends DetailActivity> implements Unbinder {
  protected T target;

  public DetailActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.photoId = finder.findRequiredViewAsType(source, R.id.photoId, "field 'photoId'", TextView.class);
    target.photoSol = finder.findRequiredViewAsType(source, R.id.photoSol, "field 'photoSol'", TextView.class);
    target.photoEarthDate = finder.findRequiredViewAsType(source, R.id.photoEarthDate, "field 'photoEarthDate'", TextView.class);
    target.cameraName = finder.findRequiredViewAsType(source, R.id.cameraName, "field 'cameraName'", TextView.class);
    target.roverName = finder.findRequiredViewAsType(source, R.id.roverName, "field 'roverName'", TextView.class);
    target.img_src = finder.findRequiredViewAsType(source, R.id.img_src, "field 'img_src'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.photoId = null;
    target.photoSol = null;
    target.photoEarthDate = null;
    target.cameraName = null;
    target.roverName = null;
    target.img_src = null;

    this.target = null;
  }
}
