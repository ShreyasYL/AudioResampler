import java.util.Scanner;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import com.sun.media.sound.WaveFileReader;
import com.sun.media.sound.WaveFileWriter;

public class Resa2 {
    File select;JFileChooser jfc;
    public Resa2()
    {
        jfc=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Audio Files","wav");
        jfc.setFileFilter(filter);
        int retval=jfc.showOpenDialog(null);
        if(retval==jfc.APPROVE_OPTION)
        {
            select=jfc.getSelectedFile();
        }
    }
    /*public void callToexe() throws Exception
    {
        Scanner ip=new Scanner(System.in);
        System.out.println("Please enter desired sampling rate and bit depth: ");
        int samp=ip.nextInt();
        int bitdepth=ip.nextInt();
        String cmdstr=" --rate "+Integer.toString(samp)+" --bits "+Integer.toString(bitdepth)+" "+jfc.getSelectedFile().getAbsolutePath()+" "+"C:\\Users\\vvbg\\Documents\\Test.wav";
       // ProcessBuilder pr=new ProcessBuilder("C:\\SSRC\\ssrc-1.33\\bin\\.\\ssrc.exe",cmdstr);
        //pr.start();
        Process pro=Runtime.getRuntime().exec("cmd /c cmd.exe");
    } */
    public void src2() throws Exception
    {
        System.out.println("Only Up scaling option is available");
        System.out.println("Please Enter desired sample rate");
        Scanner ip=new Scanner(System.in);
        float samprate=ip.nextFloat();

        File dst=new File("test.wav");
        WaveFileReader wfr=new WaveFileReader();
        AudioInputStream ais=wfr.getAudioInputStream(select);
        AudioFormat srcfrm=ais.getFormat();
        float factor=samprate/srcfrm.getSampleRate();
        AudioFormat.Encoding enc=new AudioFormat.Encoding("PCM_SIGNED");
        AudioFormat trgfrm=new AudioFormat(enc,srcfrm.getSampleRate()*factor,srcfrm.getSampleSizeInBits(),srcfrm.getChannels(),srcfrm.getFrameSize(),srcfrm.getFrameRate()*factor,srcfrm.isBigEndian());
        AudioInputStream op=AudioSystem.getAudioInputStream(trgfrm,ais);
        WaveFileWriter wfw=new WaveFileWriter();
        wfw.write(op,Type.WAVE,dst);
        System.out.println(op.getFormat().getSampleRate());
        System.out.println(op.getFormat().getSampleSizeInBits());

    }
    public static void main(String[] args) throws Exception {
        Resa2 r=new Resa2();
        //r.callToexe();
        r.src2();
    }
}
