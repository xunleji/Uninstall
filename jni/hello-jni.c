#include <string.h>
#include <jni.h>

#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <android/log.h>
#include <unistd.h>
#include <sys/inotify.h>

/* �궨��begin */
//��0��
#define MEM_ZERO(pDest, destSize) memset(pDest, 0, destSize)

//LOG�궨��
#define LOG_INFO(tag, msg) __android_log_write(ANDROID_LOG_INFO, tag, msg)
#define LOG_DEBUG(tag, msg) __android_log_write(ANDROID_LOG_DEBUG, tag, msg)
#define LOG_WARN(tag, msg) __android_log_write(ANDROID_LOG_WARN, tag, msg)
#define LOG_ERROR(tag, msg) __android_log_write(ANDROID_LOG_ERROR, tag, msg)

/* ��ȫ�ֱ���begin */
static char c_TAG[] = "onEvent";
static jboolean b_IS_COPY = JNI_TRUE;

/*jstring
 Java_com_example_untitled_MyActivity_init( JNIEnv* env,
 jobject thiz )
 {
 jstring tag = (*env)->NewStringUTF(env, c_TAG);
 //��ʼ��log
 LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY)
 , (*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "init OK"), &b_IS_COPY));
 //fork�ӽ��̣���ִ����ѯ����
 pid_t pid = fork();
 if (pid < 0)
 {
 //����log
 LOG_ERROR((*env)->GetStringUTFChars(env, tag, &b_IS_COPY)
 , (*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "fork error !!!"), &b_IS_COPY));
 }
 else if (pid == 0)
 {
 //�ӽ�����ѯ"/data/data/com.example.untitled"Ŀ¼�Ƿ���ڣ�����������˵���ѱ�ж��
 while (1)
 {
 FILE *p_file = fopen("/data/data/com.example.untitled", "r");
 if (p_file != NULL)
 {
 fclose(p_file);
 //Ŀ¼����log
 LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY)
 , (*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "I'm OK !!!"), &b_IS_COPY));
 sleep(1);
 }
 else
 {
 //Ŀ¼������log
 LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY)
 , (*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "I'm NOT OK !!!"), &b_IS_COPY));
 //ִ������am start -a android.intent.action.VIEW -d https://www.google.com
 execlp("am", "am", "start", "-a", "android.intent.action.VIEW", "-d", "https://www.google.com", (char *)NULL);
 //4.2���ϵ�ϵͳ�����û�Ȩ�޹�����ϸ���Ҫ���� --user 0
 //execlp("am", "am", "start","--user", "0" ,"-a", "android.intent.action.VIEW", "-d", "https://www.google.com, (char *)NULL);
 }
 }
 }
 else
 {
 //������ֱ���˳���ʹ�ӽ��̱�init�����������Ա����ӽ��̽���
 }
 return (*env)->NewStringUTF(env, "Hello from JNI !");
 }*/

jstring Java_com_example_untitled_MyActivity_uninstall(JNIEnv* env,
		jobject thiz, jint version) {
	jstring tag = (*env)->NewStringUTF(env, c_TAG);

	LOG_ERROR((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
			(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "version<17"), &b_IS_COPY));

	//��ʼ��log
	LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
			(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "init OK"), &b_IS_COPY));

	//fork�ӽ��̣���ִ����ѯ����
	pid_t pid = fork();
	if (pid < 0) {
		//����log
		LOG_ERROR((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
				(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "fork failed !!!"), &b_IS_COPY));
	} else if (pid == 0) {
		//�ӽ���ע��"/data/data/pym.test.uninstalledobserver"Ŀ¼������
		int fileDescriptor = inotify_init();
		if (fileDescriptor < 0) {
			LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
					(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "inotify_init failed !!!"), &b_IS_COPY));

			exit(1);
		}

		int watchDescriptor;
		watchDescriptor = inotify_add_watch(fileDescriptor,
				"/data/data/com.example.untitled", IN_DELETE);
		if (watchDescriptor < 0) {
			LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
					(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "inotify_add_watch failed !!!"), &b_IS_COPY));

			exit(1);
		}

		//���仺�棬�Ա��ȡevent�������С=һ��struct inotify_event�Ĵ�С������һ�δ���һ��event
		void *p_buf = malloc(sizeof(struct inotify_event));
		if (p_buf == NULL) {
			LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
					(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "malloc failed !!!"), &b_IS_COPY));

			exit(1);
		}
		//��ʼ����
		LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
				(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "start observer"), &b_IS_COPY));
		size_t readBytes = read(fileDescriptor, p_buf,
				sizeof(struct inotify_event));

		//read���������̣��ߵ�����˵���յ�Ŀ¼��ɾ�����¼���ע��������
		free(p_buf);
		inotify_rm_watch(fileDescriptor, IN_DELETE);

		//Ŀ¼������log
		LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
				(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "uninstalled"), &b_IS_COPY));

		//ִ������am start -a android.intent.action.VIEW -d http://shouji.360.cn/web/uninstall/uninstall.html
		// execlp("am", "am", "start", "-a", "android.intent.action.VIEW", "-d", "http://shouji.360.cn/web/uninstall/uninstall.html", (char *)NULL);
		//4.2���ϵ�ϵͳ�����û�Ȩ�޹�����ϸ���Ҫ���� --user 0
		if (version >= 17) {
			//�����û�Ȩ�޹�����ϸ���Ҫ���� --user 0
			LOG_ERROR((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
					(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "version>17"), &b_IS_COPY));
			execlp("am", "am", "start", "--user", "0", "-a",
					"android.intent.action.VIEW", "-d", "https://www.baidu.com",
					(char *) NULL);
		} else {
			execlp("am", "am", "start", "-a", "android.intent.action.VIEW",
					"-d", "https://www.baidu.com", (char *) NULL);
		}

	} else {
		//������ֱ���˳���ʹ�ӽ��̱�init�����������Ա����ӽ��̽���
	}

	return (*env)->NewStringUTF(env, "Hello from JNI !");
}

jstring Java_com_example_untitled_MyActivity_stringFromJNI(JNIEnv* env,
		jobject thiz) {
	jstring tag = (*env)->NewStringUTF(env, c_TAG);
	LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
			(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "init OK"), &b_IS_COPY));
	return (*env)->NewStringUTF(env, "Hello from JNI !");
}
