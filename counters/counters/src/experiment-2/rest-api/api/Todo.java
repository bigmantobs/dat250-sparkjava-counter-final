package api;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desc;
    private String sum;

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return ("To-Do [summary = " + sum + ", description = " + desc + "]");
    }

    public String jSon() {
        Gson gs = new Gson();
        String jSon = gs.toJson(this);
        return jSon;
    }

}
